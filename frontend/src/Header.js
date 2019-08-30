import React, { useContext } from 'react';
import { AppBar, Toolbar, Typography } from '@material-ui/core';
import { makeStyles } from '@material-ui/styles';

import LoginButton from './LoginButton';
import UserContext from './UserContext';
import UserInformation from './UserInformation';

const useStyles = makeStyles(() => ({
  root: {
    flexGrow: 1
  },
  title: {
    flexGrow: 1
  }
}));

function Header() {
  const classes = useStyles();

  const { currentUser } = useContext(UserContext);

  return (
    <div className={classes.root}>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" className={classes.title}>
            Happenings
          </Typography>
          {currentUser ? (
            <UserInformation user={currentUser} />
          ) : (
            <LoginButton />
          )}
        </Toolbar>
      </AppBar>
    </div>
  );
}

export default Header;
