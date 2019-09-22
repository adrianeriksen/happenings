import React from 'react';
import { Button } from '@material-ui/core';
import { AccountCircle } from '@material-ui/icons';

function UserInformation({ user }) {
  return (
    <>
      <Button color="inherit">
        <AccountCircle />
        &nbsp; {user.name}
      </Button>
    </>
  );
}

export default UserInformation;
