import React from 'react';

const UserInformation = ({ user, deauthenticate }) => (
  <>
    <button type="button">{user.name}</button>
    <button type="button" onClick={deauthenticate}>
      Log out
    </button>
  </>
);

export default UserInformation;
