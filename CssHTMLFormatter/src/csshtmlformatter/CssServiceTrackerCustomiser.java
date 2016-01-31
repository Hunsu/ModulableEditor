package csshtmlformatter;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import csscodemarker.services.ICssCodeMarker;

public class CssServiceTrackerCustomiser implements ServiceTrackerCustomizer<ICssCodeMarker, ICssCodeMarker> {
	
	private BundleContext context;

	public CssServiceTrackerCustomiser(BundleContext context) {
		this.context = context;
	}

	@Override
	public ICssCodeMarker addingService(ServiceReference<ICssCodeMarker> reference) {
		final ICssCodeMarker service = context.getService(reference);
		System.out.println("A new \"ICssMarker\" appeared");
		return service;
	}

	@Override
	public void modifiedService(ServiceReference<ICssCodeMarker> reference, ICssCodeMarker service) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removedService(ServiceReference<ICssCodeMarker> reference, ICssCodeMarker service) {
		// TODO Auto-generated method stub

	}

}
