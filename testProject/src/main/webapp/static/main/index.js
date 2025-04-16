'use strict';

const clientForm = document.clientForm,
submitBtn = clientForm.submitBtn;

async function addClient(){
	try{
		await axios.post('/postJson',Object.fromEntries(new FormData(clientForm)));
		
		location.href="/postJson";
	}catch(err){
        console.log('데이터를 전송중 오류 발생');
        console.log(err.message);
    }
}

submitBtn.addEventListener('click',addClient);