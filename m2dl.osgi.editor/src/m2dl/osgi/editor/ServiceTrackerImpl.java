package m2dl.osgi.editor;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class ServiceTrackerImpl implements ServiceTrackerCustomizer<IJavaHTMLFormatter, IJavaHTMLFormatter>{
	
	private BundleContext context;

	public ServiceTrackerImpl(BundleContext context) {
		this.context = context;
	}

	@Override
	public IJavaHTMLFormatter addingService(ServiceReference<IJavaHTMLFormatter> reference) {
		final IJavaHTMLFormatter service = context.getService(reference);
		return service;
	}

	@Override
	public void modifiedService(ServiceReference<IJavaHTMLFormatter> reference, IJavaHTMLFormatter service) {
	}

	@Override
	public void removedService(ServiceReference<IJavaHTMLFormatter> reference, IJavaHTMLFormatter service) {
		System.out.println("[IHTMLFormatter] bundle was removed");
		
	}

}
