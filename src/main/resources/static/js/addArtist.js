
let formElements = document.querySelector("form.artistForm").elements;
document
    .querySelector("form.artistForm")
    .addEventListener("submit", function (stop) {
        stop.preventDefault();


        let formElements = document.querySelector("form.artistForm").elements;

        let name = formElements["name"].value;
   

        // JSON object for data
        let data = {

            "name": name

        }

        addArtist(data);
        window.location.replace("browse.html?page=artists")
        //console.log(data);


    });

function addArtist(data) {
    fetch('http://localhost:8082/artists/create', {
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