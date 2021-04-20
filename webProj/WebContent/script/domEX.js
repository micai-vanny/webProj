/*
 * domEX.js
 */

// title 함수 만들기
function createTitle() {
    console.log(arguments); // 코드 하단에 createTitle()의
    // () 안에 있는 arguments들을 확인.

    var tr = document.createElement('tr');
    for (var i = 0; i < arguments.length; i++) {
        var th = document.createElement('th');
        th.innerHTML = arguments[i];
        tr.appendChild(th);
    }

    tbl.appendChild(tr);
}

// 데이터를 테이블로 생성하는 함수 만들기
function createData() {
    for (var p of persons) {
        var tr = document.createElement('tr');
        tr.setAttribute('align', 'center');
        tr.setAttribute('id', p.id);    // tr의 id값을 키값으로 지정
        tr.onmouseover = mouseOverFunc; // 마우스를 올리면 배경색 라벤더
        tr.onmouseout = mouseOutFunc; // 마우스를 내리면 배경색 없음

        for (var filed in p) {
            if (filed == 'id') {    // id를 클릭할 때 마다 input에 해당 필드의 값 출력
                var td = document.createElement('td');
                td.onclick = modifyFunc;
                td.innerHTML = p[filed];
                tr.appendChild(td);
            } else if (filed == 'name') {
                var td = document.createElement('td');
                // td.innerHTML = p[filed];
                var link = document.createElement('a');
                link.setAttribute('href', 'dom.jsp?name='+p.name+'&id='+p.id+'&score='+p.score+'&gender='+p.gender);
                link.innerHTML = p.name;
                td.appendChild(link);
                tr.appendChild(td);
            } else {
                var td = document.createElement('td');
                td.innerHTML = p[filed];
                tr.appendChild(td);
            }

        }

        var td1 = document.createElement('td'); // button이 들어갈 새로운 td추가
        var buttons = document.createElement('button'); // button 생성
        buttons.innerHTML = "삭제"; // button Label 넣어주기
        buttons.onclick = deleteRow; // button 클릭 시 삭제

        td1.appendChild(buttons); // td1에 button 넣어주기
        tr.appendChild(td1); // tr에 td1 추가
        tbl.appendChild(tr);

    }
}

// 세부 function들
function mouseOverFunc() {
    this.style.backgroundColor = 'lavender';
}

function mouseOutFunc() {
    this.style.backgroundColor = '';
}

function deleteRow() {
    this.parentNode.parentNode.remove();
}

function modifyFunc() {
    console.log(this);
    var idVal = this.innerHTML; // id를 this로 기준
    var nameVal = this.previousSibling.innerHTML; //id 앞의 name(previousSibling)의 값(.innerHTML) 받아옴
    var scoreVal = this.nextSibling.innerHTML; // id 뒤의 score(nextSibling)의 값(.innerHTML) 받아옴
    var genderVal = this.parentNode.childNodes[3].innerHTML; // id의 부모노드(tr)의 3번째 자식(gender)의 값(.innerHTML) 받아옴
    console.log(this.parentNode);
    console.log(idVal, nameVal, scoreVal, genderVal);

    document.getElementById('name').value = nameVal; // 위에 만든 input의 name에 nameVal 값을 리턴.
    document.getElementById('id').value = idVal;
    document.getElementById('score').value = scoreVal;
    var genders = document.querySelectorAll('input[name="gender"]');
    for (var i = 0; i < genders.length; i++) {
        if (genders[i].value == genderVal) {
            genders[i].checked = true;
        }
    }
    console.log(genders);
}

function saveBtnFnc() {
    var iName = document.getElementById('name');
    var iId = document.querySelector('input[name="id"]');
    var iScore = document.getElementsByTagName('input')[2];
    var iGender = document.querySelector('input[name="gender"]:checked');
    // console.log(iGender.value);

    // 마우스 over/out function 호출
    var tr = document.createElement('tr');
    tr.setAttribute('align', 'center');
    tr.onmouseover = mouseOverFunc;
    tr.onmouseout = mouseOutFunc;

    // name
    var td = document.createElement('td');
    td.innerHTML = iName.value;
    tr.appendChild(td);

    // id
    var td = document.createElement('td');
    td.innerHTML = iId.value;
    tr.appendChild(td);

    // score
    var td = document.createElement('td');
    td.innerHTML = iScore.value;
    tr.appendChild(td);

    // gender
    var td = document.createElement('td');
    td.innerHTML = iGender.value;
    tr.appendChild(td);

    //button
    var td1 = document.createElement('td'); // button이 들어갈 새로운 td추가
    var buttons = document.createElement('button'); // button 생성
    buttons.innerHTML = "삭제"; // button Label 넣어주기

    buttons.onclick = deleteRow; // button 클릭시 삭제

    td1.appendChild(buttons); // td1에 button 넣어주기
    tr.appendChild(td1); // tr에 td1 추가
    tbl.appendChild(tr);

    tbl.appendChild(tr);
}

function modifyBtnFunc() {
    var id = document.getElementById('id').value;
    var targetTr = document.getElementById(id);
    targetTr.children[0].innerHTML = 
        document.getElementById('name').value;
    targetTr.children[2].innerHTML = 
        document.getElementById('score').value;
    targetTr.children[3].innerHTML = 
        document.querySelector('input[name="gender"]:checked').value;
}