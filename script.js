let students = JSON.parse(localStorage.getItem("students")) || [];

function render() {
    let list = document.getElementById("list");
    list.innerHTML = "";

    students.forEach((s, index) => {
        let li = document.createElement("li");
        li.innerHTML = s + " <button onclick='deleteStudent(" + index + ")'>Delete</button>";
        list.appendChild(li);
    });
}

function addStudent() {
    let name = document.getElementById("name").value;
    if (name === "") return;

    students.push(name);
    localStorage.setItem("students", JSON.stringify(students));
    render();
}

function deleteStudent(index) {
    students.splice(index, 1);
    localStorage.setItem("students", JSON.stringify(students));
    render();
}

render();