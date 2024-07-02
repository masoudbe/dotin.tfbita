import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import AttributeValueGroup from './attribute-value-group';
import AttributeValueGroupDetail from './attribute-value-group-detail';
import AttributeValueGroupUpdate from './attribute-value-group-update';
import AttributeValueGroupDeleteDialog from './attribute-value-group-delete-dialog';

const AttributeValueGroupRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<AttributeValueGroup />} />
    <Route path="new" element={<AttributeValueGroupUpdate />} />
    <Route path=":id">
      <Route index element={<AttributeValueGroupDetail />} />
      <Route path="edit" element={<AttributeValueGroupUpdate />} />
      <Route path="delete" element={<AttributeValueGroupDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default AttributeValueGroupRoutes;
