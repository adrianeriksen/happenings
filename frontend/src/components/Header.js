import React from 'react';
import { AppBar, Toolbar } from '@material-ui/core';
import { makeStyles } from '@material-ui/styles';

import UserInformation from './UserInformation';
import AnchorLink from './shared/AnchorLink';
import ButtonLink from './shared/ButtonLink';

const useStyles = makeStyles(() => ({
  root: {
    flexGrow: 1
  },
  title: {
    flexGrow: 1
  }
}));

function Header({ isAuthenticated, principal }) {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      <AppBar position="static">
        <Toolbar>
          <AnchorLink
            to="/"
            label="Happenings"
            variant="h6"
            color="inherit"
            className={classes.title}
          />
          {isAuthenticated ? (
            <UserInformation user={principal} />
          ) : (
            <ButtonLink to="/login" label="Login" color="inherit" />
          )}
        </Toolbar>
      </AppBar>
    </div>
  );
}

export default Header;
