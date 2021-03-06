package org.msk86.ygoroid.newaction.dueldisk.dispatcherimpl;

import org.msk86.ygoroid.newaction.Action;
import org.msk86.ygoroid.newaction.Dispatcher;
import org.msk86.ygoroid.newaction.dueldisk.actionimpl.*;
import org.msk86.ygoroid.newcore.Controllable;
import org.msk86.ygoroid.newcore.Item;
import org.msk86.ygoroid.newcore.constant.FieldType;
import org.msk86.ygoroid.newcore.impl.*;
import org.msk86.ygoroid.newop.impl.Drag;

import java.util.ArrayList;
import java.util.List;

public class DragDispatcher implements Dispatcher<Drag> {
    @Override
    public List<Action> dispatch(Drag op) {
        List<Action> actionChain = new ArrayList<Action>();

        if (op.getItem() == null) {
            if (op.getStartDrag().getItem() instanceof InfoBar) {
                actionChain.add(new OpenMenuAction(op));
            }
        } else {
            if (op.getContainer() instanceof Field) {
                Field field = (Field) op.getContainer();
                Item targetItem = field.getItem();
                if (targetItem == null) {
                    if (op.getStartDrag().getContainer() instanceof HandCards) {
                        Card card = (Card) op.getItem();
                        if (card.isOpen()) {
                            if (field.getType() == FieldType.MONSTER || field.getType() == FieldType.EX_MONSTER) {
                                actionChain.add(new SummonAction(op));
                            } else {
                                actionChain.add(new EffectAction(op));
                            }
                        } else {
                            if (field.getType() == FieldType.MONSTER || field.getType() == FieldType.EX_MONSTER) {
                                actionChain.add(new SetMonsterAction(op));
                            } else {
                                actionChain.add(new SetCardAction(op));
                            }
                        }
                    } else {
                        actionChain.add(new MoveCardAction(op));
                    }
                }
                if (op.getItem() instanceof Card && (targetItem instanceof Controllable)) {
                    actionChain.add(new OverRayAction(op));
                }
                if ((op.getItem() instanceof Controllable) && targetItem instanceof Deck) {
                    actionChain.add(new ToDeckAction(op));
                }
            } else if (op.getContainer() instanceof HandCards) {
                if (op.getItem() instanceof Card) {
                    actionChain.add(new AddHandCardAction(op));
                }
            } else if(op.getItem() instanceof InfoBar) {
                actionChain.add(new AddHandCardAction(op));
            }
            if (actionChain.size() == 0) {
                actionChain.add(new RevertDragAction(op));
            }
        }

        return actionChain;
    }
}
