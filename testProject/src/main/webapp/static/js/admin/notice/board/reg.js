
const buttons = document.querySelectorAll('button');

buttons.forEach(btn => {
	btn.addEventListener('click', () => {
		const pageNum = document.querySelector('.hidden').dataset.pagenum;
		const searchField = document.querySelector('.hidden').dataset.searchfield;
		const searchWord = document.querySelector('.hidden').dataset.searchword;
		const url = "/admin/notice/list?pageNum=" + pageNum + "&searchField=" + searchField + "&searchWord=" + searchWord;
		switch (btn.name) {
			case 'regBtn':
				register();
				location.href = url;
				break;
			case 'cancel':
				location.href = url;
				break;
		}
	});
});

async function register() {

	const title = document.querySelector('#title').value;
	const content = document.querySelector('#content').value;
	const pub = document.querySelector('#pub').checked;

	const registerData = {
		title,
		writer_id: "admin",
		content,
		hit: 0,
		files: "파일 아직 없음",
		pub,
	};

	try {
		await axios.post('/admin/notice/board/register', registerData);
		
	} catch (error) {
		alert('등록 실패');
		console.error(error);
	}
}

