function getGenre(id) {
    fetch('http://localhost:8082/artists/read/' + id)
        .then(
            function (response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }
                // Examine the text in the response
                response.json().then(function (artistData) {
                    console.log(artistData)
                    let artistName = document.getElementById("artistName");
                    artistName.innerHTML = "Artist: " + artistData["name"];

                    let gallery = document.getElementById("allAlbums");
                    gallery.innerHTML = '';

                    for (let album of artistData["albums"]) {
                        gallery.insertAdjacentHTML("beforeend", albumCard(album["id"], album["name"], artistData["name"]));
                    }
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function albumCard(id, title, artist) {
    return '<div class="tile">\
    <a href="/album.html?id='+id+'">\
    <img src="img/album/alterbridge.png">\
    <h3>'+title+'</h3>\
    <p>By: '+ artist +'</p>\
    </a>\
</div>'
}

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const id = urlParams.get('id');
console.log(id);

getGenre(id);