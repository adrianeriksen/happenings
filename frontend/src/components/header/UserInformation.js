import React from 'react';

const UserInformation = ({ user, deauthenticate }) => (
  <div class="user-information">
    <button type="button">{user.name}</button>
    <button type="button" onClick={deauthenticate}>
      Log out
    </button>
  </div>
);

export default UserInformation;
