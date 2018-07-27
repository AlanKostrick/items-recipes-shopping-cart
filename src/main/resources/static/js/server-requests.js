const recipeFindButton = document.querySelector('.find-recipe button');
const tagInput = document.querySelector('#tag');
const itemInput = document.querySelector('#item');
const recipesList = document.querySelector('.recipes-list ul');


const xhr = new XMLHttpRequest()
xhr.onreadystatechange = function(){
	if(xhr.readyState===4 && xhr.status === 200) {
		const res = xhr.responseText
		recipesList.innerHTML = res
	}
}

recipeFindButton.addEventListener('click', function() {
	postRecipes(tagInput.value, itemInput.value)
	tagInput.value = ""
	itemInput.value = ""
})


function postRecipes(tagName,itemName) {
	xhr.open('POST', '/get-recipes/tags/' + tagName +'/items/' + itemName, true)
	xhr.send()
}


