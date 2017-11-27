package by.andruhovich.task.entity;

import by.andruhovich.task.type.SymbolType;

public class ComponentSymbol implements Component {
    private SymbolType symbolType;
    private Character symbol;

    public ComponentSymbol(SymbolType symbolType, Character symbol) {
        this.symbolType = symbolType;
        this.symbol = symbol;
    }

    public SymbolType getSymbolType() {
        return symbolType;
    }

    public Character getSymbol() {
        return symbol;
    }

    public void setSymbolType(SymbolType symbolType) {
        this.symbolType = symbolType;
    }

    public void setSymbol(Character symbol) {
        this.symbol = symbol;
    }

    public int countTextItem() {
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComponentSymbol)) return false;

        ComponentSymbol that = (ComponentSymbol) o;

        if (getSymbolType() != that.getSymbolType()) return false;
        return getSymbol().equals(that.getSymbol());
    }

    @Override
    public int hashCode() {
        int result = getSymbolType().hashCode();
        result = 31 * result + getSymbol().hashCode();
        return result;
    }

    public String toString() {
        return symbol.toString();
    }
}
