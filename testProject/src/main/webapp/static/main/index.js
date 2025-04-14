'use strict';

const btn = document.querySelector('button'),
    main = document.querySelector('main');

const client = {
    name: 'psy',
    clientNumber: 2,
    address: '수원시 팔당구',
	    phoneNumber: '010-1234-5678',
};

async function postClientData() {
    try {
        /* 
            axios 의 post 메서드를 이용함으로써, js 객체를 별도의 변환없이 JSON 형식으로
         자동 변환하여 전송.
            post 메서드의 2번째 인자에는 js 객체뿐만아니라, 숫자나 일반 문자열도 전달 가능.
         단, 이경우 숫자 0 을 전달되는 경우에는 서블릿에서 readLine() 메서드로 읽을 때,
         null 로 취환되어 수신됨에 주의.
        */
        const response = await axios.post('/postJson', client);

        main.innerHTML = response.data;
    } catch (err) {
        console.log(err);
    }
}

btn.addEventListener('click', postClientData);