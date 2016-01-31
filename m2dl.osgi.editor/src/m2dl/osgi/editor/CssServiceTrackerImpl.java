package m2dl.osgi.editor;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class CssServiceTrackerImpl implements ServiceTrackerCustomizer<ICssHTMLFormatter, ICssHTMLFormatter> {

	private BundleContext context;

	public CssServiceTrackerImpl(BundleContext context) {
		this.context = context;
	}

	@Override
	public ICssHTMLFormatter addingService(ServiceReference<ICssHTMLFormatter> reference) {
		final ICssHTMLFormatter service = context.getService(reference);
		return service;
	}

	@Override
	public void modifiedService(ServiceReference<ICssHTMLFormatter> reference, ICssHTMLFormatter service) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removedService(ServiceReference<ICssHTMLFormatter> reference, ICssHTMLFormatter service) {
		// TODO Auto-generated method stub

	}

}
