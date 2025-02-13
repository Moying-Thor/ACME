package artifact.diagram.edit.policies;

import java.util.Iterator;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

import artifact.diagram.edit.commands.ArtifactAssetArtifactPropertyCreateCommand;
import artifact.diagram.edit.commands.ArtifactAssetArtifactPropertyReorientCommand;
import artifact.diagram.edit.commands.ArtifactAssetRelationshipCreateCommand;
import artifact.diagram.edit.commands.ArtifactAssetRelationshipReorientCommand;
import artifact.diagram.edit.parts.ArtifactAssetArtifactPropertyEditPart;
import artifact.diagram.edit.parts.ArtifactAssetRelationshipEditPart;
import artifact.diagram.part.ArtifactVisualIDRegistry;
import artifact.diagram.providers.ArtifactElementTypes;

/**
 * @generated
 */
public class PropertyItemSemanticEditPolicy extends ArtifactBaseItemSemanticEditPolicy {

	/**
	* @generated
	*/
	public PropertyItemSemanticEditPolicy() {
		super(ArtifactElementTypes.Property_2109);
	}

	/**
	* @generated
	*/
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		View view = (View) getHost().getModel();
		CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(getEditingDomain(), null);
		cmd.setTransactionNestingEnabled(false);
		for (Iterator<?> it = view.getTargetEdges().iterator(); it.hasNext();) {
			Edge incomingLink = (Edge) it.next();
			if (ArtifactVisualIDRegistry.getVisualID(incomingLink) == ArtifactAssetRelationshipEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (ArtifactVisualIDRegistry.getVisualID(incomingLink) == ArtifactAssetArtifactPropertyEditPart.VISUAL_ID) {
				DestroyReferenceRequest r = new DestroyReferenceRequest(incomingLink.getSource().getElement(), null,
						incomingLink.getTarget().getElement(), false);
				cmd.add(new DestroyReferenceCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
		}
		for (Iterator<?> it = view.getSourceEdges().iterator(); it.hasNext();) {
			Edge outgoingLink = (Edge) it.next();
			if (ArtifactVisualIDRegistry.getVisualID(outgoingLink) == ArtifactAssetRelationshipEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (ArtifactVisualIDRegistry.getVisualID(outgoingLink) == ArtifactAssetArtifactPropertyEditPart.VISUAL_ID) {
				DestroyReferenceRequest r = new DestroyReferenceRequest(outgoingLink.getSource().getElement(), null,
						outgoingLink.getTarget().getElement(), false);
				cmd.add(new DestroyReferenceCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
		}
		EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation == null) {
			// there are indirectly referenced children, need extra commands: false
			addDestroyShortcutsCommand(cmd, view);
			// delete host element
			cmd.add(new DestroyElementCommand(req));
		} else {
			cmd.add(new DeleteCommand(getEditingDomain(), view));
		}
		return getGEFWrapper(cmd.reduce());
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req)
				: getCompleteCreateRelationshipCommand(req);
		return command != null ? command : super.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getStartCreateRelationshipCommand(CreateRelationshipRequest req) {
		if (ArtifactElementTypes.ArtifactAssetRelationship_4101 == req.getElementType()) {
			return getGEFWrapper(new ArtifactAssetRelationshipCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if (ArtifactElementTypes.ArtifactAssetArtifactProperty_4102 == req.getElementType()) {
			return getGEFWrapper(new ArtifactAssetArtifactPropertyCreateCommand(req, req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(CreateRelationshipRequest req) {
		if (ArtifactElementTypes.ArtifactAssetRelationship_4101 == req.getElementType()) {
			return getGEFWrapper(new ArtifactAssetRelationshipCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if (ArtifactElementTypes.ArtifactAssetArtifactProperty_4102 == req.getElementType()) {
			return getGEFWrapper(new ArtifactAssetArtifactPropertyCreateCommand(req, req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientRelationshipCommand(ReorientRelationshipRequest req) {
		switch (getVisualID(req)) {
		case ArtifactAssetRelationshipEditPart.VISUAL_ID:
			return getGEFWrapper(new ArtifactAssetRelationshipReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}

	/**
	 * Returns command to reorient EReference based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientReferenceRelationshipCommand(ReorientReferenceRelationshipRequest req) {
		switch (getVisualID(req)) {
		case ArtifactAssetArtifactPropertyEditPart.VISUAL_ID:
			return getGEFWrapper(new ArtifactAssetArtifactPropertyReorientCommand(req));
		}
		return super.getReorientReferenceRelationshipCommand(req);
	}

}
