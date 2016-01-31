package javahtmlformatter;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import javacodemarker.services.IJavaCodeMarker;

public class JavaServiceTrackerCustomiser implements ServiceTrackerCustomizer<IJavaCodeMarker, IJavaCodeMarker> {
	
	private BundleContext context;

	public JavaServiceTrackerCustomiser(BundleContext context) {
		this.context = context;
	}

	@Override
	public IJavaCodeMarker addingService(ServiceReference<IJavaCodeMarker> reference) {
		final IJavaCodeMarker service = context.getService(reference);
		System.out.println("A new \"IJavaMarker\" appeared");
		return service;
	}

	@Override
	public void modifiedService(ServiceReference<IJavaCodeMarker> reference, IJavaCodeMarker service) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removedService(ServiceReference<IJavaCodeMarker> reference, IJavaCodeMarker service) {
		// TODO Auto-generated method stub

	}

}
