/**
 * dom3.js
 */

var names = [];
		names[0] = '유정모';
		names.push('구자혁');	// 배열 맨 뒤에 하나씩 추가
		names.push('석정원');
		names.pop(); // 배열 마지막 인덱스 지움
		delete names[0];	// 원하는 배열인덱스 안의 값을 지움(공간은 그대로)
		names.shift();	// 배열의 첫번째 인덱스를 지움 (공간도 삭제)
		names.unshift('김이담');	// 배열 맨 앞에 추가
		names.push('공도현');
		names.push('소국진');
		names.push('전형민');
		
		// map얘는 foreach랑 다름. return값이 있음.
		var returnVal = names.map(function(val, idx, ary) {
			var person = {};	// person의 object 생성
			person.name = val;
			person.num = idx;
			
			return person;
		});
		
		console.log(returnVal);
		console.log("=======");
		//              returnVal : 배열을 담고 있는 object
		var returnFil = returnVal.filter(function(val, idx, ary) {
			return idx % 2 == 0;
		});

	/* 	var show = document.getElementById('show');
		var ulTag = document.createElement('ul');
		
		show.appendChild(ulTag);
		
		// forEach에 callback 함수가 매개값으로 온다... ㅎ
		names.forEach(function(val, idx, ary) {
			//console.log(`names 요소 : ${val}, ${idx}, ${ary}`);
			//console.log('names 요소: ' + i);
			var liTag = document.createElement('li');
			liTag.innerHTML = val;	// <li>김이담</li>
			ulTag.appendChild(liTag);
		}); */
				
		console.log(returnFil);