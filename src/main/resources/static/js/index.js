function getPopAlbums() {
    fetch('http://localhost:8082/albums/read')
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

                    let gallery = document.getElementById("popAlbums");
                    gallery.innerHTML = '';

                    let albumSize = Object.keys(albumData).length;
                    console.log(albumSize);
                    for (let i = 0; i < albumSize; i++) {
                        console.log(i);
                        if(i < 5){
                            console.log(albumData[i]);
                            gallery.insertAdjacentHTML("beforeend", albumCard(albumData[i]["id"],albumData[i]["name"], albumData[i]["artist"]["name"], albumData[i]["cover"]));
                        }
                        else{
                            break
                        }
                    }
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function getPopPlaylist() {
    fetch('http://localhost:8082/playlists/read')
        .then(
            function (response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }
                // Examine the text in the response
                response.json().then(function (playlistData) {
                    console.log(playlistData)

                    let gallery = document.getElementById("popPlaylist");
                    gallery.innerHTML = '';

                    let playlistSize = Object.keys(playlistData).length;
                    console.log(playlistSize);
                    for (let i = 0; i < playlistSize; i++) {
                        console.log(i);
                        if(i < 5){
                            console.log(playlistData[i]);
                            gallery.insertAdjacentHTML("beforeend", playlistCard(playlistData[i]["id"],playlistData[i]["name"], playlistData[i]["user"]["username"], playlistData[i]["artwork"]));
                        }
                        else{
                            break
                        }
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
    <img id="cover" src="'+cover+'">\
    <h3>'+title+'</h3>\
    <p>By: '+artist+'</p>\
    </a>\
</div>'
}

function playlistCard(id, title, artist, cover) {
    return '<div class="tile">\
    <a href="/playlist.html?id='+id+'">\
    <img id="cover" src="'+cover+'">\
    <h3>'+title+'</h3>\
    <p>By: '+artist+'</p>\
    </a>\
</div>'
}

getPopAlbums();
getPopPlaylist();