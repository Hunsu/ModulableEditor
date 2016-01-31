package csshtmlformatter;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import csscodemarker.services.ICssCodeMarker;
import csshtmlformatter.services.CssHTMLFormatter;
import m2dl.osgi.editor.ICssHTMLFormatter;

public class Activator implements BundleActivator {

	public static ServiceTracker<ICssCodeMarker, ICssCodeMarker> cssServiceTracker;

	public static ServiceTracker<ICssCodeMarker, ICssCodeMarker> getCssServiceTracker() {
		return cssServiceTracker;
	}

	/*
	 * (non-Cssdoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.
	 * BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		final CssHTMLFormatter htmlFormatter = new CssHTMLFormatter();
		context.registerService(ICssHTMLFormatter.class.getName(), htmlFormatter, new Hashtable<>());
		final ServiceTrackerCustomizer<ICssCodeMarker, ICssCodeMarker> trackerCustomizer = new CssServiceTrackerCustomiser(
				context);
		cssServiceTracker = new ServiceTracker<ICssCodeMarker, ICssCodeMarker>(context, ICssCodeMarker.class.getName(),
				trackerCustomizer);
		cssServiceTracker.open();
		System.out.println("A tracker for \"ICssMarker\" is started.");
	}

	/*
	 * (non-Cssdoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		System.out.println("[IHTMLFormatter] Goodbye World!!");
	}

}
