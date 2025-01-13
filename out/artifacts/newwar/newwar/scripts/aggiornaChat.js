$(document).ready(function () {
    $('.chat-input-container').on('submit', function (e) {
        e.preventDefault();

        const messaggio = $('.chat-input').val();
        if (messaggio.trim() === "") return;

        $.ajax({
            url: 'invioMessaggioServlet',
            type: 'POST', // Metodo HTTP
            data: { messaggio: messaggio },
            success: function (response) {

                $('.chat-box').append(`
                    <div class="message self">
                        <span class="message-text">${messaggio}</span>
                    </div>
                `);

                $('.chat-input').val('');
                $('.chat-box').scrollTop($('.chat-box')[0].scrollHeight);
            },
            error: function () {
                alert('Errore durante l\'invio del messaggio. Riprova.');
            }
        });
    });
});
