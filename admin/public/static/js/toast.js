Toast = {
    success: function (message) {
        Swal.fire({

            icon: 'success',
            title: message,
            showConfirmButton: false,
            timer: 2000
        })
    },

    error: function (message) {
        Swal.fire({

            icon: 'error',
            title: message,
            showConfirmButton: false,
            timer: 2000
        })
    },

    warning: function (message) {
        Swal.fire({

            icon: 'warning',
            title: message,
            showConfirmButton: false,
            timer: 2000
        })
    }
};