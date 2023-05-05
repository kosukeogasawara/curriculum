let numbers = [2, 5, 12, 13, 15, 18, 22];
	for(let i = 0; i < numbers.length; i++) {
		if(numbers[i] %2 === 0) {
			isEven(numbers[i]);
function isEven(num) {
    console.log(num + 'は偶数です');
    	}
    }
}

class Car {
	constructor(Num, Gass) {
		this.Num = Num;
		this.Gass = Gass;
	}
	
	getNumGass() {
		console.log(`ガソリンは${this.Gass}です。ナンバーは${this.Num}です。`)
	}
}
let drive = new Car(69, 15.5);
drive.getNumGass();