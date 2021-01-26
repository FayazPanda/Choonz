const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const id = urlParams.get('id');

let formElements = document.querySelector("form.genreForm").elements;
document
    .querySelector("form.genreForm")
    .addEventListener("submit", function (stop) {
        stop.preventDefault();


        let formElements = document.querySelector("form.genreForm").elements;

        let name = formElements["title"].value;
        let desc = formElements["desc"].value;
   

        // JSON object for data
        let data = {

            "name": name,
            "description": desc

        }

        addGenre(data);
        window.location.replace("browse.html?page=genres")
        //console.log(data);


    });

function addGenre(data) {
    fetch('http://localhost:8082/genres/create', {
        method: 'post', 
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        },
        body: JSON.stringify(data)
    })
        .then(function (data) {
            console.log('Request succeeded with JSON response', data);
            fillPage();
        })
        .catch(function (error) {
            console.log('Request failed', error);
        })
}