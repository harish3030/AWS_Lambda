<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Red Mate-Blood Bank Web services</title>
    <style {csp-style-nonce}>
          .menu {
            padding: .4rem 2rem;
        }
    </style>
</head>

   
<body>

    <div class="menu">
        <ul>
            <li>
                <a href="localhost:8080/users">
                    Get users list
                </a>
            </li>
            <li>
                <a href="localhost:8080/banks">
                    Get banks list
                </a>
            </li>
            <li>
                <form action="localhost:8080/users/1/requests" method="post">
                <?= csrf_field() ?>

                <label for="request_id">Request Id</label>
                <input type="input" name="request_id" /><br />

                <label for="blood_group">Blood group</label>
                <input type="input" name="blood_group" /><br />

                <label for="units">Units</label>
                <input type="input" name="units" /><br />
                
                <input type="submit" name="submit" value="Submit a request" />
                </form>
            </li>
        </ul>
    </div>

</body>
</html>

