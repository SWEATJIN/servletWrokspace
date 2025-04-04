'use strict';

/* 
    js 에서 임포트시 경로는 html 기준이 아닌 js 가 기준이됨에 주의.
    js 에서는 확장자(.js) 생략 불가.
*/
import { APP_API_URL } from '../js/config.js';

const { dataTrans } = document,
    { submitBtn } = dataTrans;

function transData(e) {
    e.preventDefault();

    dataTrans.action = `${APP_API_URL}test4`;
    dataTrans.submit();
}

submitBtn.addEventListener('click', transData);