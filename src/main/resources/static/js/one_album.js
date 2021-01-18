function getAlbum(id) {
    fetch('http://localhost:8082/albums/read/' + id)
        .then(
            function (response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }

                // Examine the text in the response
                response.json().then(function (albumData) {
                    console.log(albumData)
                    let title = document.getElementById("albumTitle");
                    title.innerHTML = '';

                    title.insertAdjacentHTML("beforeend", albumData["name"]);
                    
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

const queryString = window.location.search;

const urlParams = new URLSearchParams(queryString);

const id = urlParams.get('id');

console.log(id);

getAlbum(id);