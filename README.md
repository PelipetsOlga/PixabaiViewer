Android test task Pixabay Viewer

Prerequisites
https://pixabay.com/api/docs/#api_search_images

Requirements
The user should be able to search for images, entering one or more words in a text field
Request the Pixabay API to show the images associated with the text provided by the user and parse the JSON response. Display a list of results. Each entry should show:
A thumbnail of the image. The Pixabay username. A list of the image’s tags.
Cache the result for offline handling.
A click on the list item opens a dialog asking the user if he wants to see more details. In case of a positive answer, a new detail screen should be opened.
The detail screen should contain:
A bigger version of the image. The name of the user.
A list of the image’s tags.
The number of likes.
The number of downloads.
The number of comments.
When the app starts, it should trigger a search for the string “fruits”.
