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
                    if(getPermission()==1){
                        let playlistDataHTML = document.getElementById("all");
                        playlistDataHTML.insertAdjacentHTML("beforeend", '<button id="delete" style="visibility: visible" class="btn btn-success" href="#" onclick="addGenre();">Add</button>');
                      
                    }
                   
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
function addGenre() {
window.location.replace("addGenre.html");
}
function card(id, title) {
    return '<div class="tile">\
    <a href="/genre.html?id=' + id + '">\
    <h3>' + title + '</h3>\
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
                    let albumName = document.getElementById("name");
                    albumName.innerHTML = "All Albums";

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
    <a href="/album.html?id=' + id + '">\
    <img id="albumCover" src="' + cover + '">\
    <h3>' + title + '</h3>\
    <p>By: ' + artist + '</p>\
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

function artistCard(id, title) {
    return '<div class="tile">\
    <a href="/artist.html?id=' + id + '">\
    <h3>' + title + '</h3>\
    </a>\
</div>'
}

function getPlaylists() {
    fetch('http://localhost:8082/playlists/read/')
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
                    let playlist = document.getElementById("name");
                    playlist.innerHTML = "All Playlists";

                    let gallery = document.getElementById("all");
                    gallery.innerHTML = '';

                    for (let playlist of data) {
                        gallery.insertAdjacentHTML("beforeend", playlistCard(playlist["id"], playlist["name"], playlist["artwork"], playlist.user["username"]));
                    }
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function playlistCard(id, title, cover, user) {
    return '<div class="tile">\
    <a href="/playlist.html?id=' + id + '">\
    <img id="albumCover" src="' + cover + '">\
    <h3>' + title + '</h3>\
    <p>By: ' + user + '</p>\
    </a>\
</div>'
}


function myPlaylists() {
    fetch('http://localhost:8082/playlists/search/?search=user.id:' + userId())
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
                    let playlist = document.getElementById("name");
                    playlist.innerHTML = "All Playlists";

                    let gallery = document.getElementById("all");
                    gallery.innerHTML = '';

                    for (let playlist of data) {
                        gallery.insertAdjacentHTML("beforeend", playlistCard(playlist["id"], playlist["name"], playlist["artwork"], username()));
                    }
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const page = urlParams.get('page');
console.log(page);

if (page == "genres") {
    getGenre();
} else if (page == "albums") {
    getAlbums();
} else if (page == "artists") {
    getArtist();
} else if (page == "playlists") {
    getPlaylists();
} else if (page == "myplaylists") {
    if (userId() > 0) {
        myPlaylists();
    } else {
        alert("You are not currently logged in. Please log in to view your playlists");
    }

}