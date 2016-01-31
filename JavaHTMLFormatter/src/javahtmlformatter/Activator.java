package javahtmlformatter;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import javacodemarker.services.IJavaCodeMarker;
import javahtmlformatter.services.JavaHTMLFormatter;
import m2dl.osgi.editor.IJavaHTMLFormatter;

public class Activator implements BundleActivator {

	public static ServiceTracker<IJavaCodeMarker, IJavaCodeMarker> javaServiceTracker;

	public static ServiceTracker<IJavaCodeMarker, IJavaCodeMarker> getJavaServiceTracker() {
		return javaServiceTracker;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.
	 * BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		final IJavaHTMLFormatter htmlFormatter = new JavaHTMLFormatter();
		context.registerService(IJavaHTMLFormatter.class.getName(), htmlFormatter, new Hashtable<>());
		final ServiceTrackerCustomizer<IJavaCodeMarker, IJavaCodeMarker> trackerCustomizer = new JavaServiceTrackerCustomiser(
				context);
		javaServiceTracker = new ServiceTracker<IJavaCodeMarker, IJavaCodeMarker>(context,
				IJavaCodeMarker.class.getName(), trackerCustomizer);
		javaServiceTracker.open();
		System.out.println("A tracker for \"IJavaMarker\" is started.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		System.out.println("[IHTMLFormatter] Goodbye World!!");
	}

}
