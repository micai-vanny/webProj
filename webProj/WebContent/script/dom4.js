var ps = document.querySelector('div>p');
		ps.textContent = 'hello';
		ps.style.backgroundColor = 'yellow';
		// ps.forEach(function(val) {
		// 	console.log(val);
		// 	val.textContent = '<h1>hello</h1>';
		// 	val.style.backgroundColor = 'green';
		// });

		// 자바스크립트에서는 배열이 아주 중요...
		// 자바에는 컬렉션이 있지만 자스엔 그딴거 업지 ㅎ
		
		//numbers => 1 ~ 10
		// filter, map, forEach => 짝수만 걸러내도록 => evenVal;
		// evenVal => 리턴된 값n * 3; => mapVal;
		// mapVal => console.log(각각 출력);
		
		var numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
		
		var evenVal = numbers.filter(function(val, idx, ary){
			return val % 2 == 0;
		});
		
		var mapVal = evenVal.map(function(val, idx, ary){
			return val * 3;
		});
		
		console.log(evenVal);
		console.log(mapVal);
		
		mapVal.forEach(function(val, idx, ary) {
			console.log(val);
		});
		
		// 위의 코드를 람다식으로 바꾸면 아래와 같음......
		// numbers.filter((val) => val % 2 ==0)
		// 	.map((val) => val *3)
		// 	.forEach((val) => console.log(val));
		
		// var sum = (a, b) => a + b;	// arrow function....
		
		// sum(10,20);