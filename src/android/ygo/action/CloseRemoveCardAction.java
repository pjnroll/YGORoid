package android.ygo.action;

import android.ygo.core.*;
import android.ygo.op.Operation;

public class CloseRemoveCardAction extends BaseAction {
    public CloseRemoveCardAction(Operation operation) {
        super(operation.getDuel(), operation.getContainer(), operation.getItem());
    }

    @Override
    public void execute() {
        Card card = null;
        if(container instanceof Field) {
            if(item instanceof Card) {
                card = (Card) item;
                ((Field) container).removeItem();
            } else if(item instanceof Overlay) {
                card = ((Overlay)item).removeTopCard();
            }

        } else if(container instanceof HandCards) {
            card = (Card) item;
            ((HandCards)container).remove(card);
        }

        CardList removed = (CardList) duel.getDuelFields().getRemovedField().getItem();
        removed.push(card, true);
    }
}