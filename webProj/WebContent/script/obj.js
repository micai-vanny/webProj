/**
 * 
 */

		var num1, num2, result;
		num1 = 25;	// 문자열로 저장
		num2 = 2;
		result = num1 * num2;
		result = num1 % num2;
		
		var num3 = '25';	// 숫자로 저장
		console.log(num1===num3);	// ===:값과 타입이 모두 같은지 여부 확인
		console.log('결과값: ' + result);
		
		var obj = {}; //new Object();
		obj.name = 'Hong';
		obj.age = 20;
		obj.showInfo = function() {
			console.log('이름 : ' + obj.name + ', 나이 : ' + obj.age);
		}
		obj.showInfo();
		
		var obj2 = {
				name: 'Choi',
				age: 31,
				showInfo: function() {
					console.log('이름 : ' + this.name + ', 나이 : ' + this.age);
				}
		}
		obj2['name'] = 'Shin';
		obj2['age'] = 38;
		obj2.showInfo();

		var obj3 = {
			name: 'kang',
			age: 15
		}