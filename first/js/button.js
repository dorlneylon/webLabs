class Button {
    constructor(coord, lb, rb, text) {
        this.coord = coord;
        this.lb = lb;
        this.rb = rb;
        this.text = text;
    }

    getCoord() {
        return this.coord;
    }

    getInterval() {
        return [this.lb, this.rb];
    }

    getText() {
        return this.text;
    }

    isFloatNumber() {
        const input = this.coord.trim();
        if (input === '')
          return false;
        const floatRegex = /^[+-]?\d+(\.\d+)?$/;
        return floatRegex.test(input);
    }

    isValid() {
        return this.isFloatNumber() && parseFloat(this.coord) >= this.lb && parseFloat(this.coord) <= this.rb;
    }
}