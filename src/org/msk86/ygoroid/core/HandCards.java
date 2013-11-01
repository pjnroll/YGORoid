package org.msk86.ygoroid.core;

import android.graphics.Canvas;
import org.msk86.ygoroid.layout.LinerLayout;
import org.msk86.ygoroid.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandCards implements Item, Drawable {

    private List<Card> cards;
    private boolean set;
    private boolean forceSet;
    private boolean forceOpen;
    private LinerLayout layout;

    public HandCards() {
        cards = new ArrayList<Card>();
        set = true;
        forceSet = false;
        forceOpen = false;
        layout = new LinerLayout(cards, Utils.totalWidth(), Utils.cardHeight() / 7);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void add(Card card) {
        if (card == null) {
            return;
        }
        if (card.isToken()) {
            return;
        }
        if (!set) {
            card.open();
        } else {
            card.set();
        }
        card.positive();
        card.clearIndicator();
        cards.add(card);
    }

    public void add(List<Card> cards) {
        for (Card card : cards) {
            add(card);
        }
    }

    public Card remove(Card card) {
        if (cards.remove(card)) {
            return card;
        }
        return null;
    }

    public int size() {
        return cards.size();
    }

    public Card cardAt(int x, int y) {
        int padding = (Utils.totalWidth() - cardsWidth()) / 2;
        return layout.cardAt(x - padding, y);
    }

    private int cardPadding() {
        if (cards.size() <= 1) {
            return 0;
        }
        int maxWidth = Utils.totalWidth();
        int maxPadding = Utils.cardWidth() / 10;
        int wPadding = (maxWidth - Utils.cardWidth()) / (cards.size() - 1) - Utils.cardWidth();
        wPadding = wPadding < maxPadding ? wPadding : maxPadding;
        return wPadding;
    }

    private int cardsWidth() {
        int wPadding = cardPadding();
        return cards.size() * Utils.cardWidth() + (cards.size() - 1) * wPadding + 1;
    }

    public boolean isSet() {
        return set;
    }

    public boolean isForceSet() {
        return forceSet;
    }

    public void clearForceSet() {
        forceSet = false;
    }

    public boolean isForceOpen() {
        return forceOpen;
    }

    public void clearForceOpen() {
        forceOpen = false;
    }

    public void setAll() {
        setAll(false);
    }

    public void setAll(boolean force) {
        for (Card card : cards) {
            card.set();
        }
        set = true;
        this.forceSet = force;
        this.forceOpen = false;
    }

    public void openAll() {
        openAll(false);
    }

    public void openAll(boolean force) {
        for (Card card : cards) {
            card.open();
        }
        set = false;
        this.forceOpen = force;
        this.forceSet = false;
    }

    @Override
    public void draw(Canvas canvas, int x, int y) {
        Utils.DrawHelper drawHelper = new Utils.DrawHelper(x, y);
        drawHelper.drawDrawable(canvas, layout, drawHelper.center(width(), layout.width()), 0);
    }

    @Override
    public int width() {
        return Utils.totalWidth();
    }

    @Override
    public int height() {
        return Utils.cardHeight() + hPadding();
    }

    private int hPadding() {
        return Utils.cardHeight() / 10;
    }
}
