function showModal() {
    document.getElementById("confirmationModal").style.display = "block";
    return false;
}

function confirmDeletion() {
    document.getElementById("confirmationModal").style.display = "none";
    document.getElementById("deleteForm").submit();
}

function cancelDeletion() {
    document.getElementById("confirmationModal").style.display = "none";
}