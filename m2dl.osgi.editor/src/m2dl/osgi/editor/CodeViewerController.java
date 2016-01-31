package m2dl.osgi.editor;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CodeViewerController {

	/**
	 * The main window of the application.
	 */
	private Stage primaryStage;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	/**
	 * Radio button: indicate if the html bundle is started.
	 */
	@FXML
	private RadioMenuItem radioMenuJava;

	/**
	 * Radio button: indicate if the decorator bundle is started.
	 */
	@FXML
	private RadioMenuItem radioMenuDecorator;

	/**
	 * The viewer to display the content of the opened file.
	 */
	@FXML
	private WebView webViewer;

	/**
	 * The radio button: indicate if the css bundle is started.
	 */
	@FXML
	private RadioMenuItem radioMenuCSS;
	private BundleContext context;

	private Bundle javaBundle;
	private Bundle decoratorBundle;
	private Bundle cssBundle;

	private static String PLUGINS_PATH = "/home/meradi/workspace/osgi/plugins/";
	private static String javaBundlePath = PLUGINS_PATH + "JavaHTMLFormatter_1.0.0.201601311145.jar";
	private static String decoratorBundlePath = PLUGINS_PATH + "CodeMarker_1.0.0.201601311145.jar";
	private static String cssBundlePath = PLUGINS_PATH + "CssHTMLFormatter_1.0.0.201601311145.jar";

	/**
	 * The button "À propos" have been clicked.
	 *
	 * @param event
	 */
	@FXML
	void fireMenuAPropos(ActionEvent event) {
		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(primaryStage);
		final VBox dialogVbox = new VBox(50);
		dialogVbox.setAlignment(Pos.CENTER);
		dialogVbox.getChildren().add(new Text("This is a modulable code viewer"));
		final Scene dialogScene = new Scene(dialogVbox, 300, 80);
		dialog.setScene(dialogScene);
		dialog.show();
	}

	@FXML
	void fireMenuCloseFile(ActionEvent event) {
		webViewer.getEngine().load(null);
	}

	@FXML
	void fireMenuExit(ActionEvent event) {
		System.exit(0);
	}

	/**
	 * The button to load a bundle have been clicked.
	 *
	 * @param event
	 */
	@FXML
	void fireMenuLoadBundle(ActionEvent event) {
		final FileChooser fileChooser = new FileChooser();
		final File selectedFile = fileChooser.showOpenDialog(primaryStage);

		if (selectedFile != null) {
			Activator.logger.info("File selected: " + selectedFile.getName());
			installAndStartBundle(selectedFile.getAbsolutePath());
		} else {
			Activator.logger.info("File selection cancelled.");
		}
	}

	/**
	 * The button to open a file have been clicked.
	 *
	 * @param event
	 */
	@FXML
	void fireMenuOpenFile(ActionEvent event) {
		final FileChooser fileChooser = new FileChooser();
		final File selectedFile = fileChooser.showOpenDialog(primaryStage);
		if (selectedFile != null) {
			Activator.logger.info("File selected: " + selectedFile.getName());
			try {
				String content = new String(Files.readAllBytes(Paths.get(selectedFile.getPath())));
				String extension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));
				if (".java".equals(extension) && javaBundle != null) {
					IJavaHTMLFormatter f = Activator.getJavaServiceTracker().getService();
					content = f.getHTML(content);
					webViewer.getEngine().loadContent(content, "text/html");
				} else if (".css".equals(extension) && cssBundle != null) {
					ICssHTMLFormatter f = Activator.getCssServiceTracker().getService();
					content = f.getHTML(content);
					webViewer.getEngine().loadContent(content, "text/html");
				} else {
					webViewer.getEngine().loadContent(content, "text/plain");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			Activator.logger.info("File selection cancelled.");
		}
	}

	@FXML
	void fireRadioMenuCSS(ActionEvent event) {
		try {
			if (decoratorBundle == null)
				fireRadioMenuDecorator(null);
			if (cssBundle == null) {
				cssBundle = installAndStartBundle(cssBundlePath);
				cssBundle.start();
				radioMenuCSS.setSelected(true);
			} else {
				cssBundle.stop();
				cssBundle.uninstall();
				cssBundle = null;
				radioMenuDecorator.setSelected(false);
			}
		} catch (BundleException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void fireRadioMenuDecorator(ActionEvent event) {
		try {
			if (decoratorBundle == null) {
				decoratorBundle = installAndStartBundle(decoratorBundlePath);
				decoratorBundle.start();
				radioMenuDecorator.setSelected(true);
			} else {
				decoratorBundle.stop();
				decoratorBundle = null;
				radioMenuDecorator.setSelected(false);
			}
		} catch (BundleException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void fireRadioMenuJava(ActionEvent event) {
		try {
			if (decoratorBundle == null)
				fireRadioMenuDecorator(null);
			if (javaBundle == null) {
				javaBundle = installAndStartBundle(javaBundlePath);
				javaBundle.start();
				radioMenuJava.setSelected(true);
			} else {
				javaBundle.stop();
				javaBundle.uninstall();
				javaBundle = null;
				radioMenuJava.setSelected(false);
			}
		} catch (BundleException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void initialize() {
		assert radioMenuJava != null : "fx:id=\"radioMenuJava\" was not injected: check your FXML file 'main-window-exercice.fxml'.";
		assert radioMenuDecorator != null : "fx:id=\"radioMenuDecorator\" was not injected: check your FXML file 'main-window-exercice.fxml'.";
		assert webViewer != null : "fx:id=\"webViewer\" was not injected: check your FXML file 'main-window-exercice.fxml'.";
		assert radioMenuCSS != null : "fx:id=\"radioMenuCSS\" was not injected: check your FXML file 'main-window-exercice.fxml'.";

	}

	public void setPrimaryStage(final Stage _stage) {
		primaryStage = _stage;
	}

	public void setContext(BundleContext context) {
		this.context = context;
	}

	public Bundle installAndStartBundle(final String fileJar) {

		final File fileBundle = new File(fileJar);
		Bundle myBundle = null;
		try {
			myBundle = context.installBundle(fileBundle.toURI().toString());
			myBundle.start();
			System.out.println("The bundle " + fileJar + " installed and started");
		} catch (final BundleException e) {
			e.printStackTrace();
		}
		return myBundle;
	}
}
