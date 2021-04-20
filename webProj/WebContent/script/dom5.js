// document가 가지고 있는 method들이 아주 중요함니다...

var buttons = document.getElementsByTagName('button');
for(var i = 0; i < buttons.length; i++){
    buttons[i].setAttribute('onclick', 'showTable()')
}


var p1 = {
    name: '성진아',
    score: 80,
    gender: '여'
}

var p2 = {
    name: '김수민',
    score: 83,
    gender: '여'
}

var p3 = {
    name: '장재우',
    score: 85,
    gender: '남'
}

for (var field in p3) {	// 필드의 개수만큼 반복.
    console.log(field, p3[field]);
}

var persons = [p1, p2, p3]; 
//<table border = '1'><tr><td>성진아</td><td>80</td></tr>....</table>

function showTable() {
var tableTag = document.createElement('table');
tableTag.setAttribute('border', '1');

for (var p of persons) {	// for of: 배열의 개수만큼 반복.
    console.log(`name 요소 : ${p.name}`);
    console.log(`score 요소 : ${p.score}`);

    var tr1 = document.createElement('tr');
    for(var field in p) {	// for in: object에서 필드의 개수만큼 반복.
        var td1 = document.createElement('td');
        td1.innerHTML = p[field];
        tr1.appendChild(td1);
    }
    tableTag.appendChild(tr1);
    // for in은 필드의 갯수만큼 반복되기 때문에 
    // 값이 늘어나도 알아서 테이블을 늘려줌

}

var show = document.getElementById('show');
show.appendChild(tableTag);
}	