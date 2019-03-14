package acme.gsn.diagram.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;

import gsn.SupportedBy;

public class SupportedByStraightConnection extends PolylineConnectionEx {

	private SupportedBy supportedBy;

	public void setSupportedBy(SupportedBy supportedBy) {
		this.supportedBy = supportedBy;
	}
	
	@Override
	protected void outlineShape(Graphics g) {
		super.outlineShape(g);
		if (supportedBy != null) {
			Point midPoint = getPoints().getMidpoint();
			if (supportedBy.isIsMany()) {
				CircleDecoration circleDecoration = new CircleDecoration();
				circleDecoration.setLocation(midPoint);
				circleDecoration.setFill(true);
				g.setBackgroundColor(ColorConstants.gray);
				g.fillOval(circleDecoration.getBounds());
				g.drawOval(circleDecoration.getBounds());		
				
				String constraint = "";
				if (!supportedBy.getImplementationConstraint().isEmpty()) {
					constraint = supportedBy.getImplementationConstraint().get(0).getContent().getValue().get(0).getContent();
				}
			}
			else if (supportedBy.isIsOptional()) {
				CircleDecoration circleDecoration = new CircleDecoration();
				circleDecoration.setLocation(midPoint);
				circleDecoration.setFill(true);
				g.setBackgroundColor(ColorConstants.white);
				g.fillOval(circleDecoration.getBounds());
				g.drawOval(circleDecoration.getBounds());
			}
			g.setBackgroundColor(ColorConstants.black);
		}
	}
}
