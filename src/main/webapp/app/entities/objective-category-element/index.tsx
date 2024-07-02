import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import ObjectiveCategoryElement from './objective-category-element';
import ObjectiveCategoryElementDetail from './objective-category-element-detail';
import ObjectiveCategoryElementUpdate from './objective-category-element-update';
import ObjectiveCategoryElementDeleteDialog from './objective-category-element-delete-dialog';

const ObjectiveCategoryElementRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<ObjectiveCategoryElement />} />
    <Route path="new" element={<ObjectiveCategoryElementUpdate />} />
    <Route path=":id">
      <Route index element={<ObjectiveCategoryElementDetail />} />
      <Route path="edit" element={<ObjectiveCategoryElementUpdate />} />
      <Route path="delete" element={<ObjectiveCategoryElementDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default ObjectiveCategoryElementRoutes;
