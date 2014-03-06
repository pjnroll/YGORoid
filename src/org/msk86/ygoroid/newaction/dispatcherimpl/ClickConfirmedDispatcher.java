package org.msk86.ygoroid.newaction.dispatcherimpl;

import org.msk86.ygoroid.newaction.Action;
import org.msk86.ygoroid.newaction.Dispatcher;
import org.msk86.ygoroid.newaction.actionimpl.ShowCardEffectWindow;
import org.msk86.ygoroid.newaction.actionimpl.UnSelectAction;
import org.msk86.ygoroid.newcore.Item;
import org.msk86.ygoroid.newcore.impl.InfoBar;
import org.msk86.ygoroid.newop.impl.ClickConfirmed;

import java.util.ArrayList;
import java.util.List;

public class ClickConfirmedDispatcher implements Dispatcher<ClickConfirmed> {
    @Override
    public List<Action> dispatch(ClickConfirmed op) {
        List<Action> actionChain = new ArrayList<Action>();

        Item item = op.getItem();

        if(item == null) {
            actionChain.add(new UnSelectAction(op));
        }

        if(item instanceof InfoBar) {
            actionChain.add(new ShowCardEffectWindow(op));
        }

        return actionChain;
    }
}
