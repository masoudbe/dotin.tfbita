import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import TypeAttribute from './type-attribute';
import TypeAttributeDetail from './type-attribute-detail';
import TypeAttributeUpdate from './type-attribute-update';
import TypeAttributeDeleteDialog from './type-attribute-delete-dialog';

const TypeAttributeRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<TypeAttribute />} />
    <Route path="new" element={<TypeAttributeUpdate />} />
    <Route path=":id">
      <Route index element={<TypeAttributeDetail />} />
      <Route path="edit" element={<TypeAttributeUpdate />} />
      <Route path="delete" element={<TypeAttributeDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default TypeAttributeRoutes;
