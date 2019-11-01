import React from 'react';

const UserInformation = ({ user }) => (
  <button type="button">{user.name}</button>
);

export default UserInformation;
