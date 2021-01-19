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
                    //let data = Object.keys(listData[0]);

                    for (let i = 0; i < 5; i++) {
                        console.log(albumData[i]);
                        gallery.insertAdjacentHTML("beforeend", albumCard(albumData[i]["id"],albumData[i]["name"]));
                    }
                    // for (let) {
                    //     //globalTaskList.push(list["items"]);
                    //     gallery.insertAdjacentHTML("beforeend", albumCard(album["name"]));
                    // }
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function albumCard(id, title) {
    return '<div class="tile">\
    <a href="/one_album.html?id='+id+'">\
    <img src="img/album/alterbridge.png">\
    <h3>'+title+'</h3>\
    <p>Artist/Creator</p>\
    </a>\
</div>'
}

getPopAlbums();