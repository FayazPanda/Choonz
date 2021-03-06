const cookieValue = userId();
let formElements = document.querySelector("form.playlistForm").elements;

// Creates a user
document
    .querySelector("form.playlistForm")
    .addEventListener("submit", function (stop) {
        stop.preventDefault();


        let formElements = document.querySelector("form.playlistForm").elements;

        let name = formElements["name"].value;
        let desc = formElements["desc"].value;
        let art = formElements["art"].value;
        let user = cookieValue;

        // JSON object for data
        let data = {

            "name": name,
            "description": desc,
            "artwork": art,
            "user": {
                "id": user
            }

        }

        create(data);
        //console.log(data);


    });

function create(dataIn) {
    fetch("http://localhost:8082/playlists/create", {
        method: 'post',
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        },
        body: JSON.stringify(dataIn)
    })
        .then(function (data) {
            // console.log(data);
            //checkLogin(dataIn.username, dataIn.password)

            //console.log(dataIn)
            window.location.replace("browse.html?page=playlists");
            console.log('Success! Response: ', data);
        })
        .catch(function (error) {
            console.log('Failed! Response: ', error);
        });
}