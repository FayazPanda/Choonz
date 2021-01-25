function getAlbums(query) {
    let apiURL = 'http://localhost:8082/albums/search?search='
    for (let i = 0; i < query.length; i++) {
        let search;
        if(i != query.length-1){
            search = "name:*" + query[i] + "* and "
        }
        else{
            search = "name:*" + query[i] + "*"
        }
        
        apiURL = apiURL.concat(search)
    }
    
    fetch(apiURL)
        .then(
            function (response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }
                // Examine the text in the response
                response.json().then(function (albumData) {
                    // console.log("albumData:")
                    // console.log(albumData)

                    let gallery = document.getElementById("resultAlbums");
                    gallery.innerHTML = '';
                    // console.log(albumData.length);
                    if(albumData.length == 0){
                        gallery.insertAdjacentHTML("beforeend", "<h1>No Albums Found</h1>");
                    }else{
                        for (let album of albumData) {
                            gallery.insertAdjacentHTML("beforeend", albumCard(album["id"], album["name"], album["cover"]));
                            getAlbumArtist(album["id"]);
                        }
                    }
                });
            }
        )
        .catch(function (err) {
            // console.log('Fetch Error :-S', err);
        });
}

function getPlaylist(query) {
    let apiURL = 'http://localhost:8082/playlists/search?search='
    for (let i = 0; i < query.length; i++) {
        let search;
        if(i != query.length-1){
            search = "name:*" + query[i] + "* and "
        }
        else{
            search = "name:*" + query[i] + "*"
        }
        
        apiURL = apiURL.concat(search)
    }
    
    fetch(apiURL)
        .then(
            function (response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }
                // Examine the text in the response
                response.json().then(function (playlistData) {
                    // console.log("playlistData:")
                    // console.log(playlistData)

                    let gallery = document.getElementById("resultPlaylist");
                    gallery.innerHTML = '';

                    if(playlistData.length == 0){
                        gallery.insertAdjacentHTML("beforeend", "<h1>No Playlists Found</h1>");
                    }else{
                        for (let playlist of playlistData) {
                            gallery.insertAdjacentHTML("beforeend", playlistCard(playlist["id"], playlist["name"], playlist["artwork"]));
                            getPlaylistUser(playlist["id"]);
                        }
                    }
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function getAlbumArtist(id) {
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
                    let tile = document.getElementById("album"+id);
                    tile.innerHTML = "By: " + albumData.artist.name;
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function getPlaylistUser(id) {
    fetch('http://localhost:8082/playlists/read/' + id)
        .then(
            function (response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }
                // Examine the text in the response
                response.json().then(function (playlistData) {
                    // console.log(playlistData)
                    let tile = document.getElementById("playlist"+id);
                    tile.innerHTML = "By: " + playlistData.user.username;
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function albumCard(id, title, cover) {
    return '<div class="tile">\
    <a href="/album.html?id='+id+'">\
    <img id="cover" src="'+cover+'">\
    <h3>'+title+'</h3>\
    <p id="album'+ id+'">By:</p>\
    </a>\
</div>'
}

function playlistCard(id, title, cover) {
    return '<div class="tile">\
    <a href="/playlist.html?id='+id+'">\
    <img id="cover" src="'+cover+'">\
    <h3>'+title+'</h3>\
    <p id="playlist'+id+'">By:</p>\
    </a>\
</div>'
}

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const query = urlParams.get('searchQuery');

var list = query.split(" ");
console.log(list);

getAlbums(list);
getPlaylist(list);