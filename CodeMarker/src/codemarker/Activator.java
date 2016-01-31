package codemarker;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import csscodemarker.services.ICssCodeMarker;
import csscodemarker.services.impl.CssCodeMarker;
import csscodemarker.services.impl.JavaCodeMarker;
import javacodemarker.services.IJavaCodeMarker;

public class Activator implements BundleActivator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.
	 * BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		final IJavaCodeMarker javaService = new JavaCodeMarker();
		final Hashtable<String, String> dictionnary = new Hashtable<>();
		context.registerService(IJavaCodeMarker.class.getName(), javaService, dictionnary);
		
		final ICssCodeMarker cssService = new CssCodeMarker();
		context.registerService(ICssCodeMarker.class.getName(), cssService, dictionnary);
		System.out.println("[ICodeMarker] bundle is started and registered");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		System.out.println("[IJavaCodeMarker] Goodbye World!!");
	}

}
