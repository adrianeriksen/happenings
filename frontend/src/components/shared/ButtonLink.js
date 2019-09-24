import React from 'react';
import { Button } from '@material-ui/core';
import CollisionLink from './CollisionLink';

function ButtonLink(props) {
  const { label, ...rest } = props;

  return (
    <Button component={CollisionLink} {...rest}>
      {label}
    </Button>
  );
}

export default ButtonLink;
