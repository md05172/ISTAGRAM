
const photoBox = document.getElementById("photoBox");
const addBtn = document.getElementById("addBtn");

addBtn.addEventListener("click", () => {
  const div = document.createElement("div");
  div.className = "photo-item";
  div.style="margin-bottom: 10px"
  
  const input = document.createElement("input");
  input.type = "file";
  input.name = "pics";
  input.accept = "image/*";
  input.style="display: inline";
  
  const deleteBtn = document.createElement("button");
  deleteBtn.type = "button";
  deleteBtn.class = "deBtn";
  deleteBtn.innerHTML = "삭제";
  deleteBtn.addEventListener("click", () => {
    div.remove();
  });
  
  input.addEventListener("change", () => {
    if (input.files && input.files[0]) {
      const reader = new FileReader();
      reader.onload = (e) => {
        const img = document.createElement("img");
        img.style="width: 200px;height: 120px;border-radius: 10%";
        img.src = e.target.result;
        photoBox.appendChild(img);
        div.insertBefore(img, deleteBtn);
      };
      reader.readAsDataURL(input.files[0]);
      
      input.style="display:contents";
      deleteBtn.style="margin-left: 55px;"
    }
  });
  
  div.appendChild(input);
  div.appendChild(deleteBtn);
  photoBox.appendChild(div);
});