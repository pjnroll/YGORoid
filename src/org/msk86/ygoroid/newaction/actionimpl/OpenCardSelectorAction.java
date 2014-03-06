package org.msk86.ygoroid.newaction.actionimpl;

import org.msk86.ygoroid.newcore.Listable;
import org.msk86.ygoroid.newcore.impl.CardSelector;
import org.msk86.ygoroid.newcore.impl.Deck;
import org.msk86.ygoroid.newcore.impl.InfoBar;
import org.msk86.ygoroid.newop.Operation;

public class OpenCardSelectorAction extends BaseAction {
    public OpenCardSelectorAction(Operation operation) {
        super(operation);
    }

    @Override
    public void execute() {
        Listable listable = null;
        if(item instanceof InfoBar && ((InfoBar) item).getInfoItem() instanceof Listable) {
            listable = (Listable) ((InfoBar) item).getInfoItem();
        }
        if(item instanceof Deck) {
            listable = (Deck) item;
        }
        if(listable != null) {
            CardSelector selector = new CardSelector(listable, listable.listCards());
            duel.setCardSelector(selector);
        }
    }
}
