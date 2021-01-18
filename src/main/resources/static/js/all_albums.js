function getAlbums() {
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

                    let row = document.getElementById("albumPlaceHolder");
                    row.innerHTML = '';
                    //let data = Object.keys(listData[0]);

                    for (let album of albumData) {
                        //globalTaskList.push(list["items"]);
                        row.insertAdjacentHTML("beforeend", albumCard(album["name"]));
                    }
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function albumCard(title) {
    return '<div class="col-md-4">\
    <div class="card">\
        <img src="https://www.webfx.com/blog/images/cdn.designinstruct.com/files/582-how-to-image-placeholders/generic-image-placeholder.png"\
            class="card-img-top" alt="...">\
        <div class="card-body">\
            <a href="#" class="stretched-link">\
                <h5 class="card-title">' + title + '</h5>\
                <p class="card-text">Some quick example text to build on the album and some artist / track data</p>\
            </a>\
        </div>\
    </div>\
</div>'
}

getAlbums();