function getGenre() {
    fetch('http://localhost:8082/genres/read')
        .then(
            function (response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }
                // Examine the text in the response
                response.json().then(function (data) {
                    console.log(data)
                    let name = document.getElementById("name");
                    name.append("Genres");
                    let gallery = document.getElementById("all");
                    gallery.innerHTML = '';

                    for (let genre of data) {
                        gallery.insertAdjacentHTML("beforeend", card(genre["id"], genre["name"],));
                    }
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function card(id, title) {
    return '<div class="tile">\
    <a href="/genre.html?id='+id+'">\
    <h3>'+title+'</h3>\
    </a>\
</div>'
}


function getAlbums() {
    fetch('http://localhost:8082/albums/read/')
        .then(
            function (response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }
                // Examine the text in the response
                response.json().then(function (data) {
                    console.log(data)
                    let artistName = document.getElementById("name");
                    artistName.innerHTML = "Artist: " + data["name"];

                    let gallery = document.getElementById("all");
                    gallery.innerHTML = '';

                    for (let album of data) {
                        gallery.insertAdjacentHTML("beforeend", albumCard(album["id"], album["name"], album.artist["name"], album["cover"]));
                    }
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function albumCard(id, title, artist, cover) {
    return '<div class="tile">\
    <a href="/album.html?id='+id+'">\
    <img id="albumCover" src="'+cover+'">\
    <h3>'+title+'</h3>\
    <p>By: '+ artist +'</p>\
    </a>\
</div>'
}

function getArtist() {
    fetch('http://localhost:8082/artists/read')
        .then(
            function (response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }
                // Examine the text in the response
                response.json().then(function (data) {
                    console.log(data)
                    let name = document.getElementById("name");
                    name.append("Artists");
                    let gallery = document.getElementById("all");
                    gallery.innerHTML = '';

                    for (let artist of data) {
                        gallery.insertAdjacentHTML("beforeend", artistCard(artist["id"], artist["name"],));
                    }
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}
function artistCard(id, title, artist, cover) {
    return '<div class="tile">\
    <a href="/artist.html?id='+id+'">\
    <h3>'+title+'</h3>\
    </a>\
</div>'
}

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const page = urlParams.get('page');
console.log(page);

if(page=="genres"){
    getGenre();
}
else if(page=="albums"){
    getAlbums();
}
else if(page=="artists"){
    getArtist();
}
else if(page=="playlists"){

}
else if(page=="myplaylists"){

}
