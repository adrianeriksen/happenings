import React from 'react';

const UserInformation = ({ user, deauthenticate }) => (
  <div className="user-information">
    <button type="button">{user.name}</button>
    <button type="button" onClick={deauthenticate}>
      Log out
    </button>
  </div>
);

export default UserInformation;
